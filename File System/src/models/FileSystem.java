package src.models;

public abstract class FileSystem {
    private String name;
    private Directory parent;
    private long createdTime;

    public FileSystem(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        createdTime = System.currentTimeMillis();
    }

    public boolean delete() {
        if (parent == null)
            return false;
        return parent.deleteEntry();
    }

    public getCreationTime() {
        return createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}