package com.app.models.binding;

/**
 * Created by vilimir on 04.05.17.
 */
public class UpdateRepairBinding {
    private long id;
    private String description;
    private boolean isDone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
