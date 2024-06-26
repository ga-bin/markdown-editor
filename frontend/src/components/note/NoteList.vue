<template>
  <v-list lines="three" density="comfortable"  size="x-large">
    <Note v-for="(note, index) in noteListStore.noteList"
      :key="note.noteId"
      :note="note"
      @noteClicked="changeNote"
    />
    <infinite-loading style="visibility:hidden" @infinite="loadMoreData"></infinite-loading>
  </v-list>
</template>
<script setup>
import { ref, onMounted, computed } from 'vue';
import { useNoteListStore } from '@/store/NoteListStore';
import { useNoteStore } from '@/store/NoteStore';

import Note from '../note/Note';

const noteListStore = useNoteListStore();
const noteStore = useNoteStore();
const noteList = noteListStore.noteList;
const hasNext = noteListStore.hasNext;


function changeNote(clickedNoteId) {
  noteListStore.changeNote(clickedNoteId);
  noteStore.getNote(clickedNoteId);
}

function loadMoreData() {
  if(noteListStore.noteList.length > 0) {
    noteListStore.cursor = noteListStore.noteList[noteListStore.noteList.length-1].noteId;
  }

  if(noteListStore.hasNext) {
    noteListStore.getAllNote();
  }
}

</script>
<style scoped>
.spinner {
  display: none;
}
</style>