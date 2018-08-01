<template>
  <v-app id="inspire">
    <!--  头部导航  -->
    <app-toolbar></app-toolbar>
    <v-content>
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </v-content>
    <mo-alert></mo-alert>
    <mo-dialog></mo-dialog>
    <mo-loading></mo-loading>
  </v-app>
</template>

<script>
import AppToolbar from './layout/toolbar'
import AppNavigation from './layout/navigation'
export default {
  components: {
    AppNavigation,
    AppToolbar
  },
  data: () => ({
  }),
  props: {
    source: String
  }
}
</script>

<style>
  .fade-enter-active, .fade-leave-active {
    transition: opacity .5s ease;
  }
  .fade-enter, .fade-leave-active {
    opacity: 0
  }
  .list__tile {
    font-size: 14px;
  }
  html {
    font-size: 12px;
  }
  a {
    text-decoration: none;
  }
</style>
