package org.usfirst.frc2264.subsystems;

import java.util.ArrayList;

import org.usfirst.frc2264.RobotParts;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class VisionSubsystem extends Subsystem {
	public class ParticleReport implements Comparable<ParticleReport> { // TODO Wat be?
		double PercentAreaToImageArea;
		double Area;
		double ConvexHullArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		
		public int compareTo(ParticleReport r) {
			return (int)(r.Area - this.Area);
		}
	};

	private USBCamera camera;
	private boolean boxCanBeSeen;
	private double boxDirection, boxDistance;
	
	public VisionSubsystem(int frontLeft, int frontRight, int rearLeft, int rearRight) {
		this.camera = new USBCamera();
	}
	
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
	public boolean canSeeBox() { return this.boxCanBeSeen; }
	public double getBoxDirection() { return this.boxDirection; }
	public double getBoxDistance() { return this.boxDistance; }
	public void repollCamera() {
		Image image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0),
				binaryImage/* ? */ = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1]; // TODO Why is this a 1-arg array?
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, 0.5, 100.0, 0, 0);
		criteria[0].lower = (float)0.5;
		NIVision.ParticleFilterOptions2 options = new NIVision.ParticleFilterOptions2(0, 0, 1, 1); // TODO Figure out ALL OF THESE
		
		// Get an image
		camera.getImage(image);
		NIVision.imaqColorThreshold(binaryImage, image, 255, NIVision.ColorMode.RGB,
				new NIVision.Range(0xCF, 0xFF),
				new NIVision.Range(0xCF, 0xFF),
				new NIVision.Range(0x00, 0x3F));
		NIVision.imaqParticleFilter4(binaryImage, binaryImage, criteria, options, null); // TODO Most of these args. Find their purpose.
		int numberOfParticles = NIVision.imaqCountParticles(binaryImage, 1); // TODO Find what `connectivity8' is.
		if(numberOfParticles > 0) {
			ArrayList<ParticleReport> particles = new ArrayList<>();
			for(int i = 0; i < numberOfParticles; ++i) {
				ParticleReport particle = new ParticleReport();
				particle.PercentAreaToImageArea  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				particle.Area  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_AREA);
				particle.ConvexHullArea  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
				particle.BoundingRectTop  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				particle.BoundingRectLeft  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				particle.BoundingRectBottom  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				particle.BoundingRectRight  = NIVision.imaqMeasureParticle(binaryImage, i, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				particles.add(particle);
			}
			particles.sort(null);
			Scores scores = new Scores();
			scores.Trapezoid = TrapezoidScore(particles.get(0));
			scores.LongAspect = LongSideScore(particles.get(0));
			scores.ShortAspect = ShortSideScore(particles.get(0));
			scores.AreaToConvexHullArea = ConvexHullAreaScore(particles.get(0));
			final double SCORE_MIN = 75.0;
			if(scores.Trapezoid < SCORE_MIN || scores.AreaToConvexHullArea < SCORE_MIN || (scores.LongAspect < SCORE_MIN && scores.ShortAspect < SCORE_MIN)) {
				this.boxCanBeSeen = false;
			} else {
				this.boxCanBeSeen = true;
				this.boxDistance = computeDistance(binaryImage, particles.get(0), scores.LongAspect > scores.ShortAspect);
			}
		} else {
			this.boxCanBeSeen = false;
		}
		
		CameraServer.getInstance().setImage(binaryImage);
		Timer.delay(0.050); // 50msec
	}
	
	// TODO See if any of the below can be optimised away
    public class Scores
    {
        double Trapezoid;
        double LongAspect;
        double ShortAspect;
        double AreaToConvexHullArea;
    };	
	/**
	 * Converts a ratio with ideal value of 1 to a score. The resulting function is piecewise
	 * linear going from (0,0) to (1,100) to (2,0) and is 0 for all inputs outside the range 0-2
	 */
	double ratioToScore(double ratio)
	{
		return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
	}

	double AreaScore(ParticleReport report)
	{
		double boundingArea = (report.BoundingRectBottom - report.BoundingRectTop) * (report.BoundingRectRight - report.BoundingRectLeft);
		//Tape is 7" edge so 49" bounding rect. With 2" wide tape it covers 24" of the rect.
		return ratioToScore((49/24)*report.Area/boundingArea);
	}

	/**
	 * Method to score if the aspect ratio of the particle appears to match the retro-reflective target. Target is 7"x7" so aspect should be 1
	 */
	double AspectScore(ParticleReport report)
	{
		return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop)));
	}

    /**
     * Method to score convex hull area. This scores how "complete" the particle is. Particles with large holes will score worse than a filled in shape
     */
    double ConvexHullAreaScore(ParticleReport report)
    {
            return ratioToScore((report.Area/report.ConvexHullArea)*1.18);
    }
    
    /**
     * Method to score if the particle appears to be a trapezoid. Compares the convex hull (filled in) area to the area of the bounding box.
     * The expectation is that the convex hull area is about 95.4% of the bounding box area for an ideal tote.
     */        
    double TrapezoidScore(ParticleReport report)
    {
            return ratioToScore(report.ConvexHullArea/((report.BoundingRectRight-report.BoundingRectLeft)*(report.BoundingRectBottom-report.BoundingRectTop)*.954));
    }

    /**
     * Method to score if the aspect ratio of the particle appears to match the long side of a tote.
     */
    double LongSideScore(ParticleReport report)
    {
            return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop))/(26.9 / 12.1));
    }

    /**
     * Method to score if the aspect ratio of the particle appears to match the short side of a tote.
     */
    double ShortSideScore(ParticleReport report)
    {
            return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop))/(16.9 / 12.1));
    }
    double computeDistance (Image image, ParticleReport report, boolean isLong)
    {
        double normalizedWidth, targetWidth;
        NIVision.GetImageSizeResult size;

        size = NIVision.imaqGetImageSize(image);
        normalizedWidth = 2*(report.BoundingRectRight - report.BoundingRectLeft)/size.width;
        targetWidth = isLong ? 26.0 : 16.9;

        return  targetWidth/(normalizedWidth*12*Math.tan(RobotParts.CAMERA_ANGLE*Math.PI/(180*2)));
    }
}
