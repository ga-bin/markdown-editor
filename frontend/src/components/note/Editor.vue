<template lang="">
    <div class="markdown-editor">
        <textarea v-model="noteStore.content" @input="updateMarkdown" placeholder="Enter Markdown here...">

        </textarea>
        <div class="preview" v-html="output"></div>
      </div>
</template>
<script setup>
import marked from '../../plugins/marked.js';
import { onMounted, onUpdated } from 'vue';
import { useNoteStore } from '@/store/NoteStore';
import { ref } from 'vue';

const noteStore = useNoteStore();
const output = ref('');

onMounted(() => {
  updateMarkdown();
})

onUpdated(() => {
  updateMarkdown();
})
function updateMarkdown() {
  output.value = marked(noteStore.content);
}


</script>
<style>
.markdown-editor {
  display: flex;
  gap: 20px;
  width: 1000vh;
  height: 100%;
}

textarea {
  width: 100%;
  height: 100%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.preview {
  width: 100%;
  padding: 20px;
  height: 100%;
  border: 1px solid #ccc;
  border-radius: 5px;
  overflow: auto;
  background-color: #f7f7f7;
}

.preview ul, .preview ol {
  padding-left: 30px;
  padding-top: 5px;
}


</style>