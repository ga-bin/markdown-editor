<template>
    <v-app-bar title="NOTE APP" color="deepPurple">
        <SaveModal />
        <DeleteButtonModal v-if="deleteBtnState"/>
    </v-app-bar>
</template>
<script setup>
import { onMounted, watch, ref } from 'vue';
import SaveModal from '../note/SaveModal';
import DeleteButtonModal from '../note/DeleteButtonModal';
import { useNoteStore } from '@/store/NoteStore';
import { useNoteListStore } from '@/store/NoteListStore';

const noteStore = useNoteStore();
const noteListStore = useNoteListStore();
const deleteBtnState = ref(false);
const noteId = ref(noteStore.noteId);


watch(() => noteStore.noteId, (newValue) => {
    deleteBtnState.value = noteStore.noteId == 0 ? false : true;
}) 

async function insertNote() {
    noteStore.insertNote(noteListStore.getAllNote);
}


</script>
<style scoped>

</style>