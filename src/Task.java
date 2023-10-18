public class Task {
    private final String title;
    private final String description;
    private boolean isPriority;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isPriority = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isPriority=" + isPriority +
                '}';
    }
}