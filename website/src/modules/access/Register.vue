<template>
  <v-layout>
    <v-flex xs12 sm6 class="login-center">
      <v-tabs centered
              color="primary"
              slider-color="yellow"
              dark>
        <v-tab :href="'#tab-register'" @click="switchType(1)">手机注册</v-tab>
        <v-tab :href="'#tab-register'" @click="switchType(2)">邮箱注册</v-tab>
      </v-tabs>
      <v-tabs-items>
        <v-tabs-content :id="'tab-register'">
          <v-card>
            <v-card-text>
              <v-form v-model="registerValid" ref="registerForm" lazy-validation>
                <v-text-field
                  label="用户名"
                  v-model="user.username"
                  :rules="[v => !!v || '请输入用户名']"
                  :counter="16"
                  required
                ></v-text-field>
                <v-text-field
                  v-if="user.type === 2"
                  label="Email"
                  v-model="user.email"
                  :rules="[v => !!v || '请输入Email']"
                  required
                ></v-text-field>
                <v-text-field
                  v-if="user.type === 1"
                  label="手机号码"
                  v-model="user.phone"
                  :rules="[v => !!v || '请输入手机号码']"
                  required
                ></v-text-field>
                <v-text-field
                  label="密码"
                  v-model="user.password"
                  :rules="[v => !!v || '请输入密码']"
                  :append-icon="e2 ? 'visibility' : 'visibility_off'"
                  :append-icon-cb="() => (e2 = !e2)"
                  :type="e2 ? 'password' : 'text'"
                  required
                ></v-text-field>
                <v-text-field
                  label="确认密码"
                  v-model="user.confirmPassword"
                  :rules="[v => (user.password === user.confirmPassword) || '两次输入的密码不一致']"
                  :append-icon="e3 ? 'visibility' : 'visibility_off'"
                  :append-icon-cb="() => (e3 = !e3)"
                  :type="e3 ? 'password' : 'text'"
                  required
                ></v-text-field>
                <v-layout row>
                  <v-flex xs9>
                    <v-text-field
                      label="验证码"
                      v-model="user.code"
                      :rules="[v => !!v || '请输入验证码']"
                      :counter="6"
                      required
                    ></v-text-field>
                  </v-flex>
                  <v-flex xs3>
                    <v-btn flat @click="sendCode()" :disabled="disabledSendCode">{{sendText}}</v-btn>
                  </v-flex>
                </v-layout>
                <v-btn color="primary" block :loading="registerLoading" @click="doRegister()"  :disabled="!registerValid">注册</v-btn>
                <div class="login-right">
                  <router-link :to="{name:'Login'}">我要登录</router-link>
                </div>
              </v-form>
            </v-card-text>
          </v-card>
        </v-tabs-content>
      </v-tabs-items>
    </v-flex>
  </v-layout>
</template>

<script>
  let vm
  export default {
    data () {
      return {
        currentTab: 'tab-register',
        user: {
          type: 1
        },
        e2: true,
        e3: true,
        registerValid: true,
        registerLoading: false,
        sendText: '发送验证码',
        disabledSendCode: false
      }
    },
    methods: {
      switchType (type) {
        vm.user.type = type
      },
      sendCode () {
        let receiver = null
        if (vm.user.type === 1) {
          if (!vm.user.phone) {
            vm.$alert('请输入手机号码')
            return
          }
          receiver = vm.user.phone
        }
        if (vm.user.type === 2) {
          if (!vm.user.phone) {
            vm.$alert('请输入Email')
            return
          }
          receiver = vm.user.email
        }
        if (!receiver) {
          return
        }
        vm.axios.post('/access/code', null, {
          params: {
            type: vm.user.type,
            receiver: receiver
          }
        }).then(function (data) {
          let i = 60
          let intervalCode = setInterval(function () {
            i--
            vm.sendText = i + '秒'
            if (i <= 0) {
              intervalCode.cancel(intervalCode)
              vm.disabledSendCode = false
              vm.sendText = '发送验证码'
            }
          }, 1000)
        })
      },
      doRegister () {
        if (!this.$refs.registerForm.validate()) {
          return
        }
        vm.registerLoading = true
        vm.axios.post('/access/register', vm.user, {
          ignoreTip: true
        }).then(function (data) {
          window.location.href = './'
        }).catch((error) => {
          if (error.data) {
            vm.$alert(error.data.message, {
              position: 'top',
              color: 'red'
            })
          }
        }).finally(() => {
          vm.registerLoading = false
        })
      }
    },
    created: function () {
      vm = this
    }
  }
</script>

<style>

</style>
