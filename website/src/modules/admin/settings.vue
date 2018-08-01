<template>
  <v-layout row>
    <v-flex xs12>
      <v-card>
        <!--<v-toolbar dark>-->
          <!--<v-toolbar-side-icon></v-toolbar-side-icon>-->
          <!--<v-toolbar-title>Settings</v-toolbar-title>-->
        <!--</v-toolbar>-->
        <v-list two-line subheader>
          <v-subheader>配置</v-subheader>
          <v-list-tile avatar>
            <v-list-tile-content>
              <v-list-tile-title>清空缓存</v-list-tile-title>
              <v-list-tile-sub-title>清空redis数据字典缓存数据</v-list-tile-sub-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-btn color="red" @click="clearCache()" dark>
                <v-icon small="" left="">fa-trash</v-icon>清空
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
    export default {
      data () {
            return {}
        },
        created: function () {

        },
        methods: {
          clearCache () {
            this.$confirm('是否清除数据字典缓存？').then((data) => {
              this.axios.delete('/cache', {
                params: {
                  cacheKey: 'dictionariesCache'
                }
              })
            })
          }
        }
    }
</script>

<style>

</style>
