<template>
    <v-navigation-drawer color="beige">
        <SideStickyComponent/>
        <NoteList />
    </v-navigation-drawer>
</template>
<script setup>
import NoteList from '../note/NoteList';
import SideStickyComponent from '../SideStickyComponent';


</script>
<style scoped>
.hide-scrollbar {
  scrollbar-width: none; /* Firefox에 대한 지원 */
  -ms-overflow-style: none; /* IE 및 Edge에 대한 지원 */
}

.hide-scrollbar::-webkit-scrollbar {
  display: none; /* Chrome 및 Safari에 대한 지원 */
}

::v-deep ::-webkit-scrollbar {
  width: 0;
  background: transparent;
}
</style>