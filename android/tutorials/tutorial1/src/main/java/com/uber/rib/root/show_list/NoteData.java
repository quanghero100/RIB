package com.uber.rib.root.show_list;

/**
 * Created by cpu10639 on 09/04/2018.
 */

class NoteData {
    public NoteData(String content) {
        noteContent = content;
        noteTitle = "";
    }
    public NoteData(String title, String content) {
        noteContent = content;
        noteTitle = title;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    String noteContent;
    String noteTitle;

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
