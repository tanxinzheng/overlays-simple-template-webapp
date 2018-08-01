<template>
  <v-navigation-drawer
    color="primary"
    fixed
    clipped
    app
    v-model="drawer"
    width="160"
  >
    <v-list dense>
      <template v-for="(item, i) in items">
        <v-layout
          row
          v-if="item.heading"
          align-center
          :key="i">
          <v-flex xs6>
            <v-subheader v-if="item.heading">
              {{ item.heading }}
            </v-subheader>
          </v-flex>
          <v-flex xs6 class="text-xs-center">
            <a href="#!" class="body-2 black--text">EDIT</a>
          </v-flex>
        </v-layout>
        <v-list-group v-else-if="item.children" v-model="item.model" no-action>
          <v-list-tile slot="item" @click="go(item)">
            <v-list-tile-action>
              <v-icon>{{ item.model ? item.icon : item['icon-alt'] }}</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>
                {{ child.text }}
                <!--<router-link :to="{path:child.path}">{{ child.text }}</router-link>-->
              </v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
          <v-list-tile
            v-for="(child, i) in item.children"
            :key="i"
            @click="go(child)">
            <v-list-tile-action v-if="child.icon">
              <v-icon>{{ child.icon }}</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title>
                <!--<router-link :to="{path:child.path}">{{ child.text }}</router-link>-->
                {{ child.text }}
              </v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list-group>
        <v-list-tile v-else @click="go(item)">
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>
              {{ item.text }}
            </v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </template>
    </v-list>
    <v-divider></v-divider>
    <v-list dense class="pt-0">
      <v-list-tile>
        <!--<v-list-tile-action>-->
          <!--<v-icon>{{ item.icon }}</v-icon>-->
        <!--</v-list-tile-action>-->
        <v-list-tile-content>
          <v-list-tile-title>文档</v-list-tile-title>
        </v-list-tile-content>
      </v-list-tile>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  import Bus from '../components/factory/bus.js'
  import routerItem from '../router/route.conf'
  let items = []
  for (let nav of routerItem) {
    if (!nav.notNav) {
      items.push(nav)
    }
  }
  export default {
    name: 'app-navigation',
    data () {
      return {
        drawer: null,
        items: items
      }
    },
    methods: {
      go: function (item) {
        this.$router.push(item.path)
      }
    },
    created: function () {
      let vm = this
      Bus.$on('toggleNav', function (data) {
        vm.drawer = data
      })
    }
  }
</script>

<style>
  .list__tile__action {
    min-width: 42px;
  }
</style>
