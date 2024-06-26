import { defineStore } from 'pinia';
import axios from "axios";
const baseUrl = import.meta.env.VITE_API_URL;

export const useNoteListStore = defineStore({
    id: 'noteListStore',
    state: () => ({
        noteList : [],
        hasNext: true,
        keyword: '',
        cursor: 0
    }),
    actions: {
        getAllNote() {
            axios.get(`${baseUrl}/getAllNote?keyWord=${this.keyword}&cursor=${this.cursor}`)
                .then(res => {
                    let newNoteList = null;
                    if(this.cursor != 0) {
                        newNoteList = [...this.noteList, ...res.data.noteList];
                    } else {
                        newNoteList = res.data.noteList;
                    }
                    this.noteList = newNoteList;
                    this.hasNext = res.data.hasNext;
                    this.noteList.forEach((note) => {
                        note.isClicked = false;
                    })
                    console.log(this.noteList);
                    console.log(this.hasNext);
                })
        },

        changeNote(clickedNoteId) {
            for(let i = 0; i < this.noteList.length; i++) {
                let note = this.noteList[i];
                if(note.noteId != clickedNoteId) {
                  note.isClicked = false;
                } else {
                    note.isClicked = true;
                }
            }
            
        }
    }
})