<template>
  <span class="uploader-label" ref="btn" v-show="support">
    <slot></slot>
  </span>
</template>

<script>
  import { uploaderMixin, supportMixin } from 'vue-simple-uploader/src/common/mixins'

  const COMPONENT_NAME = 'uploader-label'

  export default {
    name: COMPONENT_NAME,
    mixins: [uploaderMixin, supportMixin],
    props: {
      directory: {
        type: Boolean,
        default: false
      },
      single: {
        type: Boolean,
        default: false
      },
      attrs: {
        type: Object,
        default () {
          return {}
        }
      }
    },
    mounted () {
      this.$nextTick(() => {
        this.uploader.assignBrowse(this.$refs.btn, this.directory, this.single, this.attrs)
      })
    }
  }
</script>

<style>
  .uploader-label {
    cursor: pointer;
    background: none;
    outline: none;
  }
  .uploader-btn:hover {
    background-color: rgba(0, 0, 0, .08);
  }
</style>
