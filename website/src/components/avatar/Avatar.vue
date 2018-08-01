<template>
  <div class="mo-avatar">
    <span class="avatar" v-if="!avatarModel[avatarUrlKey]">{{avatarLabel.slice(0, 1).toUpperCase()}}</span>
    <v-avatar :size="size" v-if="avatarModel[avatarUrlKey]">
      <img :src="avatarModel[avatarUrlKey]" :alt="avatarLabel">
    </v-avatar>
  </div>
</template>

<script>
  export default {
    name: 'mo-avatar',
    props: {
      size: {
        require: false,
        default: () => '26px'
      },
      avatarUrlKey: {
        type: String,
        default: () => 'avatarUrl'
      },
      avatarTextKey: {
        type: String,
        default: () => 'avatarText'
      },
      avatarModel: {
        type: Object,
        require: true,
        default: {}
      },
      avatarLabel: {
        type: String,
        require: false,
        default () {
          if(this.avatarTextKey){
            return this.avatarModel[this.avatarTextKey]
          }
          return ''
        }
      }
    },
    data () {
      return {
//        avatarLabel: null
      }
    },
    created () {

    },
    methods: {
    }
  }
</script>

<style>
  div.mo-avatar > span {
    min-height: 40px !important;
    min-width: 40px !important;
    line-height: 40px;
    font-size: 20px;
    color: #fff;
    background-color: gray;
    text-align: center;
    display:table-cell;
  }
</style>
