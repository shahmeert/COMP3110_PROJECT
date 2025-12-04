
import java.util.ArrayList;
import java.util.List;

public class Notebook_1 {

    private String title;
    private List<String> notes;

    public Notebook_1(String title) {
        this.title = title;
        this.notes = new ArrayList<>();
    }

    public void addNote(String note) {
        if (note != null && !note.isEmpty()) {
            notes.add(note);
        }
    }

    public boolean removeNote(int index) {
        if (index < 0 || index >= notes.size()) {
            return false;
        }
        notes.remove(index);
        return true;
    }

    public int getNoteCount() {
        return notes.size();
    }

    public String getTitle() {
        return title;
    }

    public String getNote(int index) {
        if (index < 0 || index >= notes.size()) {
            return "";
        }
        return notes.get(index);
    }

    public List<String> getAllNotesCopy() {
        return new ArrayList<>(notes);
    }
}
