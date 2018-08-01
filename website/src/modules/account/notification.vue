<template>
  <v-container>
    <v-layout>
      <v-flex xs12 sm12>
        <v-card>
          <v-card-title style="text-align: center">
            <h1>{{notification.title}}</h1>
          </v-card-title>
          <v-card-title center>
            <span>发送时间：{{notification.sendTime}}</span>
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            {{notification.body}}
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  import MoAvatar from "../../components/avatar/Avatar";
  export default {
    components: {MoAvatar},
    data () {
      return {
        notification: {}
      }
    },
    created: function () {
      let id = this.$route.params.id;
      this.getNotification(id);
      this.read(id);
    },
    methods: {
      read (id) {
        this.axios.put('/notification/' + id +'/read');
      },
      getNotification (id) {
        this.axios.get('/account/notification/' + id).then((data) => {
          this.notification = data.data
        })
      }
    }
  }
</script>

<style>

</style>
