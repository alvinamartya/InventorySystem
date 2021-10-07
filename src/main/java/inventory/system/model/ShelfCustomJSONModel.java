package inventory.system.model;

public class ShelfCustomJSONModel {
    private String id;
    private String name;
    private Integer emptyShelf;
    private Integer totalShelf;
    private Integer filled;
    private String capacityText;

    public Integer getEmptyShelf() {
        return emptyShelf;
    }

    public void setEmptyShelf(Integer emptyShelf) {
        this.emptyShelf = emptyShelf;
    }

    public Integer getTotalShelf() {
        return totalShelf;
    }

    public void setTotalShelf(Integer totalShelf) {
        this.totalShelf = totalShelf;
    }

    public Integer getFilled() {
        return filled;
    }

    public void setFilled(Integer filled) {
        this.filled = filled;
    }

    public String getCapacityText() {
        return capacityText;
    }

    public void setCapacityText(String capacityText) {
        this.capacityText = capacityText;
    }

    public ShelfCustomJSONModel() {
    }

    public ShelfCustomJSONModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
