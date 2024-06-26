<template>
    <v-chip color="deepPurple"
        v-if="chip"
        :closable="props.closable"
        @click:close="delTag"
        size="small"
    >
    {{props.value }}
    </v-chip>
</template>
<script setup>
import { ref } from 'vue';

const props = defineProps({
    value: String,
    closeable: {
        type: Boolean,
        default: false
    }
});

const emit = defineEmits(['delTag'])

const chip = ref(true);

function delTag() {
    emit('delTag', props.value);
    chip.value = false;
}
</script>
<style scoped>
    
</style>