<template>
    <v-list-item @click="handleNewNote">
        <v-list-item-title>새 글을 추가하려면 클릭하세요</v-list-item-title>
        <v-list-item-subtitle>
          새로운 마크다운 노트 입니다. 원하는 방식의 글을 마크다운으로 작성해 보세요!
        </v-list-item-subtitle>
    </v-list-item>
</template>
<script setup>
import { onMounted } from 'vue';
import { useNoteStore } from '@/store/NoteStore';
import { useNoteListStore } from '@/store/NoteListStore';

const noteStore = useNoteStore();
const noteListStore = useNoteListStore();

onMounted(() => {
    noteStore.tags = [];
})


function handleNewNote() {
    noteStore.noteId = 0;
    noteStore.title = '새 글을 추가하려면 클릭하세요';
    noteStore.content = '새로운 마크다운 노트 입니다. 원하는 방식의 글을 마크다운으로 작성해 보세요!';
    noteStore.tags = [];
    noteListStore.changeNote();

}



</script>
<style scoped>
.deep-beige-bg {
  background-color: rgb(var(--v-theme-deepPurple));
}

.v-list-item-title {
    margin-bottom: 6px;
    font-weight: 500;
}

.v-list-item-subtitle {
    font-size: 13px;
    color: gray;
}
</style>