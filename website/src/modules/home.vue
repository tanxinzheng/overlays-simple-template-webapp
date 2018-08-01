<template>
  <v-container fluid>
    <!--<x-data-table>-->
      <!--<template slot="items" slot-scope="props">-->
        <!--<tr>-->
          <!--<td>{{ props.item.name }}</td>-->
          <!--<td class="text-xs-right">{{ props.item.age }}</td>-->
        <!--</tr>-->
      <!--</template>-->
    <!--</x-data-table>-->
    <v-layout row>
      <v-flex xs4>
        <v-subheader>用户名</v-subheader>
      </v-flex>
      <v-flex xs8>
        <v-text-field
          id="testing"
          name="input-1"
          label="Label Text"
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs4>
        <v-subheader>Focus</v-subheader>
      </v-flex>
      <v-flex xs8>
        <v-text-field
          name="input-2"
          label="Label Text"
          value="Input text"
          class="input-group--focused"
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs4>
        <v-subheader>Normal with input text + label</v-subheader>
      </v-flex>
      <v-flex xs8>
        <v-text-field
          solo-inverted
          name="input-3"
        ></v-text-field>
      </v-flex>
    </v-layout>
    <v-layout row>
      <v-flex xs4>
        <v-subheader>Disabled</v-subheader>
      </v-flex>
      <v-flex xs8>
        <v-text-field
          name="input-3"
          label="Label Text"
          value="Input text"
          disabled
        ></v-text-field>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    name: '',
    data () {
      return {
        options: {
          // https://github.com/simple-uploader/Uploader/tree/develop/samples/Node.js
          target: '/api/file/upload',
          testChunks: false,
          headers: {
            Authorization : localStorage.getItem('token')
          },
          query: {
            keyword: "附件"
          }
        },
        attrs: {
          accept: 'image/*'
        },
        items : [
          {name:'1111',age: 3},
          {name:'2222',age: 41}
        ]
      }
    },

    methods: {
      complete () {
        console.log('complete', arguments)
      },
      fileComplete () {
        console.log('file complete', arguments)
      }
    }
  }
</script>

<style>

</style>
