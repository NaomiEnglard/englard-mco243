package scheduler;

public enum Priority {
	

	Low(1), Meduim(4), High(8);
	private int value;
	Priority(int num){
		this.value = num;
	}
	
	public int getNumberValue(){
		return value;
	}

}
