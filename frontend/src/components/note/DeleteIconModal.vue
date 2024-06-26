<template>
    <div>
      <v-dialog
        v-model="dialog"
        max-width="400"
        persistent
      >
        <template v-slot:activator="{ props: activatorProps }">
            <v-icon v-bind="activatorProps" 
                    class="deleteIcon"
            >mdi-trash-can</v-icon>            
        </template>
  
        <v-card
          prepend-icon="mdi-trash-can"
          text="삭제된 글은 다시 복구되지 않으니 신중하게 선택해 주세요"
          title="정말 삭제하시겠습니까?"
        >
          <template v-slot:actions>
            <v-spacer></v-spacer>
  
            <v-btn @click="deleteNote">
              삭제
            </v-btn>
  
            <v-btn @click="dialog = false">
              취소
            </v-btn>
          </template>
        </v-card>
      </v-dialog>
    </div>
  </template>
<script setup>
import { ref } from 'vue';
import { useNoteStore } from '@/store/NoteStore';
import { useNoteListStore } from '@/store/NoteListStore';

const noteStore = useNoteStore();
const noteListStore = useNoteListStore();
const props = defineProps({
  noteId: Number,
})
const dialog = ref(false);

function deleteNote() {
  noteListStore.cursor = 0;
  noteStore.deleteNote(props.noteId, noteListStore.getAllNote);
  dialog.value = false;
}


</script>
<style scoped>
.deleteIcon {
  color: red;
  float: right;
}

</style>