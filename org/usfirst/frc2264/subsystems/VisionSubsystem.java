package org.usfirst.frc2264.subsystems;

import java.util.ArrayList;
import java.util.Optional;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * @author brandonxu
 */
public class VisionSubsystem extends Subsystem {	//A structure to hold measurements of a particle
	/**
	 * @author tikiking1
	 */
	public static class BoxParams {
		public final int relativeCentre, particleWidth;
		// Direction in degrees from the centre, with right being positive
		// Distance in feet
		public BoxParams(int relativeCentre, int particleWidth) {
			this.relativeCentre = relativeCentre;
			this.particleWidth = particleWidth;
		}
	}
	public class ParticleReport implements Comparable<ParticleReport>
	{
		double PercentAreaToImageArea;
		double Area;
		double ConvexHullArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		public int compareTo(ParticleReport r)
		{
			return (int)(r.Area - this.Area);
		}
	};
	public class Scores
	{
		double Trapezoid;
		double LongAspect;
		double ShortAspect;
		double AreaToConvexHullArea;
	};
	//Images
	USBCamera camera;
	Image frame;
	Image binaryFrame;
	int imaqError;
	//Constants
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(34,  60); //Default hue range for yellow tote
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(57, 259); //Default saturation range for yellow tote
	NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(64, 254); //Default value range for yellow tote
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 26.9/12.1; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 16.9/12.1; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 75.0; //Minimum score to be considered a tote
	double VIEW_ANGLE = 51.50219228499263; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	Scores scores = new Scores();
	public VisionSubsystem()
	{
		// create images
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
		camera = new USBCamera("cam0");
		camera.openCamera();
		camera.startCapture();
		camera.setWhiteBalanceAuto();
		camera.setExposureAuto();
	}
	public Optional<BoxParams> poll()
	{
		camera.getImage(frame);
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_VAL_RANGE);
		CameraServer.getInstance().setImage(binaryFrame);
		Timer.delay(0.05);
		//filter out small particles
		float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
		criteria[0].lower = areaMin;
		imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
		//Send particle count after filtering to dashboard
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		if(numParticles > 0)
		{
			//Measure particles and sort by particle size
			ArrayList<ParticleReport> particles = new ArrayList<ParticleReport>();
			for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
			{
				ParticleReport par = new ParticleReport();
				par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.ConvexHullArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
				par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				par.BoundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
				particles.add(par);
			}
			particles.sort(null);
			//This only scores the largest particle.
			//Note that the long and short side scores expect a single tote and will not work for a stack of 2 or more totes.
			scores.Trapezoid = TrapezoidScore(particles.get(0));
			scores.LongAspect = LongSideScore(particles.get(0));
			scores.ShortAspect = ShortSideScore(particles.get(0));
			scores.AreaToConvexHullArea = ConvexHullAreaScore(particles.get(0));
			boolean isTote = scores.Trapezoid > SCORE_MIN && (scores.LongAspect > SCORE_MIN || scores.ShortAspect > SCORE_MIN) && scores.AreaToConvexHullArea > SCORE_MIN;
//			boolean isLong = scores.LongAspect > scores.ShortAspect;
			//Send distance and tote status to dashboard. The bounding rect, particularly the horizontal center (left - right) may be useful for rotating/driving towards a tote
//			SmartDashboard.putNumber("Bounding Left", particles.get(0).BoundingRectLeft);
//			SmartDashboard.putNumber("Bounding Right", particles.get(0).BoundingRectRight);
//			SmartDashboard.putNumber("Bounding Center", (particles.get(0).BoundingRectLeft + particles.get(0).BoundingRectRight) / 2);
//			SmartDashboard.putNumber("Image Center", NIVision.imaqGetImageSize(binaryFrame).width / 2);
//			SmartDashboard.putNumber("Relative Center", ((particles.get(0).BoundingRectLeft + particles.get(0).BoundingRectRight) / 2) - (NIVision.imaqGetImageSize(binaryFrame).width / 2));
//			SmartDashboard.putNumber("Width of Entire Image (in ft)", this.pixelsToFeet(NIVision.imaqGetImageSize(binaryFrame).width, isLong));
			if(isTote) {
				int relativeCentre = (int) ((particles.get(0).BoundingRectLeft + particles.get(0).BoundingRectRight) / 2) - (NIVision.imaqGetImageSize(binaryFrame).width / 2);
				int particleWidth = (int) (particles.get(0).BoundingRectRight - particles.get(0).BoundingRectLeft);
				return Optional.of(new BoxParams(relativeCentre, particleWidth));
			} else {
				return Optional.empty();
			}
		}
		else
		{
			return null;
		}
	}
	//Comparator function for sorting particles. Returns true if particle 1 is larger
	static boolean CompareParticleSizes(ParticleReport particle1, ParticleReport particle2)
	{
		//we want descending sort order
		return particle1.PercentAreaToImageArea > particle2.PercentAreaToImageArea;
	}
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
		return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop))/LONG_RATIO);
	}
	/**
	 * Method to score if the aspect ratio of the particle appears to match the short side of a tote.
	 */
	double ShortSideScore(ParticleReport report)
	{
		return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop))/SHORT_RATIO);
	}
	/**
	 * @author tikiking1
	 */
	double computeDistance (Image image, ParticleReport report, boolean isLong)
	{
		return this.pixelsToFeet(report.BoundingRectRight - report.BoundingRectLeft, isLong);
	}
	/**
	 * @author tikiking1
	 */
	private double pixelsToFeet(double width_px, boolean isLong) {
		return (isLong ? 26.0 : 16.9 * width_px) / (24 * Math.tan(VIEW_ANGLE * Math.PI / 360));
	}
	/**
	 * @author tikiking1
	 */
	protected void initDefaultCommand() {
		this.setDefaultCommand(null);
	}
}
