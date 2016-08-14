package Dto.trashPoi;

public class TrashPoi {
	
	private long id;
	private String deletedAt;
	
	public TrashPoi(long id, String deletedAt) {
		this.id = id;
		this.deletedAt = deletedAt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	

}
