package src.models;

public class Directory extends FileSystem {
    private List<FileSystem> contents;

    public Directory(String name, Directory parent) {
        super(name, parent);
        contents = new ArrayList<>();
    }

    public int getSize() {
        int size = 0;
        for (FileSystem fs : contents)
            size += fs.getSize();
        return size;
    }

    public int numberOfFiles() {
        int files = 0;
        for (FileSystem fs : contents) {
            if (fs instanceof Directory) {
                files += fs.numberOfFiles();
            } else if (fs instanceof File)
                files++;
        }
        return files;
    }

    public boolean deleteEntry(FileSystem entry) {
        contents.remove(entry);
    }

    public void addEntry(FileSystem entry) {
        contents.add(entry);
    }

    public List<FileSystem> getContents() {
        return contents;
    }
}