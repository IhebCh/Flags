package com.example.flags;

public class Country {

	private int id;
	private boolean selectedAsAnAnswer;

	public Country(int id, boolean selectedAsAnAnswer,
			boolean selectedAsMainAnswer) {
		this.id = id;
		this.selectedAsAnAnswer = selectedAsAnAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSelectedAsAnAnswer() {
		return selectedAsAnAnswer;
	}

	public void setSelectedAsAnAnswer(boolean selectedAsAnAnswer) {
		this.selectedAsAnAnswer = selectedAsAnAnswer;
	}

}
