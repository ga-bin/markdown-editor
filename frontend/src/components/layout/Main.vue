<template>
    <v-main class="d-flex align-center justify-center contentArea" color="primary">
      <Editor/>
    </v-main>
</template>
<script>
import Editor from '../note/Editor';
</script>
<style>
.contentArea {
  height: 100%;
  width: 100%;
  background-color: rgb(var(--v-theme-lightBeige));
}
</style>