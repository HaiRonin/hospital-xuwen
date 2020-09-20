<template>
    <web-view :src="src"></web-view>
</template>

<script lang="ts">

    import {Component, Vue, Ref} from 'vue-property-decorator';

    @Component
    export default class Name extends Vue {

        src = '';

        onLoad (options: IOBJ) {
            // console.log(options);
            // console.log(options.url);
            // console.log(decodeURIComponent(options.url));
            this.src = options.url;
        }

        created () {}

        mounted () {}

        activated () {}

    }
</script>

<style lang="scss" scoped>

</style>
