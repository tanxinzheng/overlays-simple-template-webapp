<template>
  <router-view></router-view>
</template>

<script>
  import nav from '../account'
  export default {
    name: '',
    data () {
      return {
        account: {},
        tabs: null,
        notifications: [],
        items: [
          { action: '15 min', headline: 'Brunch this weekend?', title: 'Ali Connors', subtitle: "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?" },
          { action: '2 hr', headline: 'Summer BBQ', title: 'me, Scrott, Jennifer', subtitle: "Wish I could come, but I'm out of town this weekend." },
          { action: '6 hr', headline: 'Oui oui', title: 'Sandra Adams', subtitle: 'Do you have Paris recommendations? Have you ever been?' },
          { action: '12 hr', headline: 'Birthday gift', title: 'Trevor Hansen', subtitle: 'Have any ideas about what we should get Heidi for her birthday?' },
          { action: '18hr', headline: 'Recipe to try', title: 'Britta Holt', subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.' }
        ]
      }
    },
    created: function () {
      this.getAccountInfo()
      this.getNotification()
    },
    methods: {
      getAccountInfo: function () {
        this.axios.get('/account').then((data) => {
          this.account = data.data
        })
      },
      getNotification () {
        this.axios.get('/account/notification', {
            params: {
                pageSize: 10,
                pageNum: 1,
            }
        }).then((data) => {
          this.notifications = data.data.data
        })
      }
    }
  }
</script>

<style>

</style>
