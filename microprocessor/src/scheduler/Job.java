package scheduler;

public class Job {

	private Priority priority;
	private int dynamicPriority;
	private long lastRanAtTime;
	private String name;
	private State state;
	private int timeLeft;
	private JobType type;

	public Job( String name,Priority priority,  JobType type, int timeLeft) {
		super();
		this.priority = priority;
		this.name = name;
		this.timeLeft = timeLeft;
		this.type = type;

	}

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public int getDynamicPriority() {
		return dynamicPriority;
	}

	public void setDynamicPriority(int dynamicPriority) {
		this.dynamicPriority = dynamicPriority;
	}

	public long getLastRanAtTime() {
		return lastRanAtTime;
	}

	public void setLastRanAtTime(long lastRanAtTime) {
		this.lastRanAtTime = lastRanAtTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public void decerementTimeLeftToRun(int time) {
		timeLeft -= time;

	}

	public boolean isFinished() {
		return timeLeft == 0 ? true : false;
	}

}
