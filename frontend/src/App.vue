<template>
  <v-layout class="rounded rounded-md layout">
    <router-view></router-view>
  </v-layout>
</template>

<script setup>
import Sidebar from './components/layout/Sidebar'
import Navbar from './components/layout/Navbar'
import Main from './components/layout/Main'
</script>

<style>
.layout {
  height: 100vh;
}
</style>
