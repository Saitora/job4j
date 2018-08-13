package ru.job4j.junior.control;

import java.util.List;

public class Info {

    private final List<Store.User> changed;
    private final List<Store.User> added;
    private final List<Store.User> deleted;

    public Info(final List<Store.User> changed, final List<Store.User> added, final List<Store.User> deleted) {
        this.changed = changed;
        this.added = added;
        this.deleted = deleted;
    }

    public int getChangedCount() {
        return changed.size();
    }

    public int getAddedCount() {
        return added.size();
    }

    public int getDeletedCount() {
        return deleted.size();
    }

    public List<Store.User> getChanged() {
        return changed;
    }

    public List<Store.User> getAdded() {
        return added;
    }

    public List<Store.User> getDeleted() {
        return deleted;
    }
}
