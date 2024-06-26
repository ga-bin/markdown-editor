import { defineStore } from "pinia";
import axios from "axios";

const baseUrl = import.meta.env.VITE_API_URL;

export const useNoteStore = defineStore({
    id: 'noteStore',
    state: () => ({
        noteId: 0,
        tags : [],
        title : '',
        content : '',
    }),
    actions: {
        pushTag(tag) {
            const tagObject = { "tagName" : tag};
            this.tags.push(tagObject);
        },

        delTag(tag) {
            const tagNames = this.tags.map(tagObject => tagObject.tagName);

            let index = tagNames.indexOf(tag);
            if(index != -1) {
                this.tags.splice(index, 1);
            }
        },

        delAllTags() {
            this.tags = [];
        },

        checkTagsMaxLength() {
            if(this.tags.length >= 3) {
                return true;
            } else {
                return false;
            }
        },

        checkTagsMinLength() {
            if(this.tags.length == 0) {
                return true;
            } else {
                return false;
            }
        },

        checkTagExist(tag) {
            const tagNames = this.tags.map(tagObject => tagObject.tagName);
            if(tagNames.includes(tag)) {
                return true;
            } else {
                return false;
            }
        },

        checkTitleMinLength() {
            if(this.title.trim() == "") {
                return true;
            } else {
                return false;
            }
        },

        delTitle() {
            this.title = '';
        },

        insertNote : function(callback) {
            const noteDTO = {
                tagDTOList : this.tags,
                title : this.title,
                content: this.content
            }

            axios.post(`${baseUrl}/insertNote`, noteDTO)
                .then(res => {
                    if(callback) {
                     callback();
                    }
                })

        },

        updateNote : function(callback) {
            const noteDTO = {
                noteId : this.noteId,
                title : this.title,
                content: this.content,
                tagDTOList : this.tags
            }

            axios.put(`${baseUrl}/updateNote`, noteDTO)
                .then(res => {
                    if(callback) {
                        callback();
                    }
                })

        },

        getNote : function(noteId) {
            axios.get(`${baseUrl}/getNote?noteId=${noteId}`)
                .then(res => {
                    let note = res.data;
                    this.noteId = note.noteId;
                    this.title = note.title;
                    this.content = note.content;
                    this.tags = note.tagDTOList;
                })
        },

        deleteNote : function(noteId, callback) {
            if(noteId == null || noteId == undefined) {
                noteId = this.noteId;
            } 

            axios.delete(`${baseUrl}/deleteNote?noteId=${noteId}`)
                .then(() => {
                    if(callback) {
                        callback();
                    }
                })
            console.log(noteId);
        }
    }

})