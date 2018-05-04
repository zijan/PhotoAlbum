package mindbeacon.codetest.photoalbum.repository;

public class Image {
	private int id;
	private long createdAt;
	private String name;
	private String imageUrl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", createdAt=" + createdAt + ", name=" + name + ", imageUrl=" + imageUrl + "]";
	}
	
}
