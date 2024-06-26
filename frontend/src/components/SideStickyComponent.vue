<template>
    <div class="sticky-search">
      <v-col cols="12">
        <v-text-field
          class="mx-auto searchBar"
          density="comfortable"
          menu-icon=""
          placeholder="검색어를 입력하세요"
          theme="light"
          variant="solo"
          auto-select-first
          item-props
          rounded
          hide-no-data
          hide-details
          @keyup.enter="searchNoteList"
          v-model="keyword"
        >
        </v-text-field>
        <v-icon class="searchIcon" @click="searchNoteList">mdi-magnify</v-icon>
      </v-col>
      <NewNote style="clear: both;"/>
      </div>
</template>
<script setup>
import NewNote from './note/NewNote';
import { ref } from 'vue';
import { useNoteListStore } from '@/store/NoteListStore'; 

const noteListStore = useNoteListStore();

const keyword = ref('');
function searchNoteList() {
  console.log(keyword.value);
  noteListStore.keyword = keyword.value;
  noteListStore.cursor = 0;
  noteListStore.getAllNote();
}

</script>
<style scoped>
.sticky-search {
  position: sticky;
  background-color: rgb(var(--v-theme-beige));
  top: 0;
  z-index: 1000;
}

.searchBar {
  padding-bottom: 0px;
}
.addNoteBtn {
  width: 230px; 
  margin-left: 13px;
}

.searchIcon {
  float: left;
  position: relative;
  top: -34px;
  left: 197px;
  color: darkgray;
  padding: 0;
  margin: 0;
}
</style>