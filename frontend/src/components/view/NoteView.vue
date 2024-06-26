<template>
    <v-layout class="rounded rounded-md layout">
      <Sidebar/>
      <Navbar />
      <Main />
    </v-layout>
  </template>
  
  <script setup>
  import Sidebar from '@/components/layout/Sidebar'
  import Navbar from '@/components/layout/Navbar'
  import Main from '@/components/layout/Main'
  </script>