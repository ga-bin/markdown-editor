<template>
    <v-dialog v-model="dialog" max-width="500" @click:outside="closeModal">
        <template v-slot:activator="{ props: activatorProps }">
          <v-btn
            v-bind="activatorProps"
            text="Save"
            variant="flat"
            class="saveBtn"
            prepend-icon="mdi-content-save"
          ></v-btn>
        </template>
         
        <template v-slot:default="{ isActive }">
            <v-card>
              <v-row justify="center">
                <v-col
                cols="12"
                md="11"
                sm="6"
              >
                <v-text-field
                  label="제목을 입력하세요"
                  required
                  class="pt-10"
                  v-model="noteStore.title"
                ></v-text-field>
              </v-col>
            </v-row>
              <v-card-text class="pt-0">
                <Tag v-for="(tag, index) in noteStore.tags"
                  :key="tag.tagName"
                  :value="tag.tagName"
                  :closable="true"
                  @delTag="noteStore.delTag"
                />
                <v-textarea
                  label="태그를 입력 후 엔터를 쳐 주세요"
                  name="input-7-1"
                  variant="filled"
                  class="textArea"
                  @keyup.enter="makeTag" 
                  v-model="tagValue"
                  auto-grow
                ></v-textarea>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  text="저장"
                  @click="saveModal"
                ></v-btn>
                <v-btn
                  text="취소" 
                  @click="closeModal " 
                ></v-btn>
              </v-card-actions>
            </v-card>
          </template>
      </v-dialog>
</template>
<script setup>
import Tag from './Tag';
import { ref, onMounted } from 'vue';
import { useNoteStore } from '@/store/NoteStore';
import { useNoteListStore } from '@/store/NoteListStore';
import { useRoute } from 'vue-router';

const route = useRoute();
const tagValue = ref('');
const noteStore = useNoteStore();
const noteListStore = useNoteListStore();
const dialog = ref(false);

onMounted(() => {
  showSaveBtn();
})


const validation = {
  tagValidation : function() {
    let trimedTagValue = tagValue.value.trim();

    if(trimedTagValue.length > 3) {
      alert('태그는 3글자까지만 등록 가능합니다.');
      tagValue.value='';
      return false;
    }
    
    if(noteStore.checkTagsMaxLength()) {
      alert('태그는 3개 까지만 등록 가능합니다.');
      tagValue.value = '';
      return false;
    }

    if(noteStore.checkTagExist(trimedTagValue)) {
      alert('이미 등록된 태그입니다.');
      tagValue.value = '';
      return false;
    }

    return true;
  },

  insertValidation : function() {
    if(noteStore.checkTagsMinLength()) {
      alert("태그는 최소 1개 이상 등록하셔야 합니다.");
      return false;
    }

    if(noteStore.checkTitleMinLength()) {
      alert("제목을 입력하셔야 합니다.");
      return false;
    }

    return true;
  }
}

function showSaveBtn() {
  const currentPath = ref(route.path).value;
  const saveBtn = document.querySelector('.saveBtn');
  console.log(currentPath);

  saveBtn.style.display = currentPath === '/note' ? 'flex' : 'none';
}

function makeTag() {
 let trimedTagValue = tagValue.value.trim();

 if(!validation.tagValidation()) {
  return;
 }

 if(trimedTagValue != '') {
  noteStore.pushTag(trimedTagValue);
  tagValue.value = '';
 }
}


function closeModal() {
  noteStore.delAllTags();
  noteStore.delTitle();
  dialog.value=false;
 }

 function saveModal() {
  if(!validation.insertValidation()) {
    return false;
  }

  noteListStore.cursor = noteListStore.noteList[noteListStore.noteList.length-1].noteId;
  if(noteStore.noteId != null && noteStore.noteId != 0) {
    noteListStore.cursor = 0;
    noteStore.updateNote(noteListStore.getAllNote);
    dialog.value=false;
  } else {
    noteStore.insertNote(noteListStore.getAllNote);
    closeModal();
  }

 }


</script>
<style scoped>
.saveBtn {
    background-color: rgb(var(--v-theme-beige));
    opacity: 0.8;
    color: blue;
    margin-right: 16px;
    display: none;
}

.textArea {
  margin-top: 30px;
}

</style>