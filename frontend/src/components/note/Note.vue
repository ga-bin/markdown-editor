<template>
    <v-list-item @click="changeBackGroundColor" :class="{ 'deep-beige-bg' : props.note.isClicked }">
        <v-list-item-title>{{props.note.title}}</v-list-item-title>
        <v-list-item-subtitle>
          {{props.note.content}}
        </v-list-item-subtitle>
        <Tag v-for="(tag, index) in props.note.tagDTOList"
          :key="tag.tagId"
          :value="tag.tagName"
        />
        <DeleteIconModal :noteId="props.note.noteId"/>
    </v-list-item>
</template>
<script setup>
import { ref } from 'vue';
import Tag from './Tag';
import DeleteIconModal from './DeleteIconModal';

const emit = defineEmits(['noteClicked'])
const props = defineProps({
  note: Object,
})


function changeBackGroundColor() {
  emit('noteClicked', props.note.noteId);
}

function deleteNote() {
  console.log('deleteNote');
}
</script>
<style scoped>
.deep-beige-bg {
  background-color: rgb(var(--v-theme-deepBeige));
}


.v-list-item-title {
    margin-bottom: 6px;
    font-weight: 500;
}

.v-list-item-subtitle {
    font-size: 13px;
    color: gray;
}

.deleteIcon {
  color: red;
  float: right;
}
</style>