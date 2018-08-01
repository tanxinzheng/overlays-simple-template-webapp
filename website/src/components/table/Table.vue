<template>
  <div>
    <div class="card" data-ripple="false" style="height: auto;">
      <v-divider></v-divider>
      <v-toolbar color="transparent" flat :height="48">
        <v-toolbar-title>
          {{title}}
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn v-for="btn in actionButtons"
               :color="btn.color"
               @click="btn.click()"
               small
               v-bind:key="btn.label">{{btn.label}}</v-btn>
        <v-tooltip v-if="showDeleteButton" bottom>
          <v-btn icon
                 slot="activator"
                 @click="remove()"
                 :disabled="selected.length === 0">
            <v-icon>delete</v-icon>
          </v-btn>
          <span>删除</span>
        </v-tooltip>
        <v-tooltip v-if="showCreateButton" bottom>
          <v-btn icon
                 @click="add()"
                 slot="activator">
            <v-icon>add</v-icon>
          </v-btn>
          <span>新增</span>
        </v-tooltip>
        <v-tooltip bottom>
          <v-btn icon
                 slot="activator"
                 :loading="buttons.search.loading"
                 :disabled="buttons.search.loading"
                 @click.native="loadData()">
            <v-icon>refresh</v-icon>
          </v-btn>
          <span>刷新</span>
        </v-tooltip>
      </v-toolbar>
      <v-divider></v-divider>
      <div style="padding: 15px 15px;" v-if="searchParams.length > 0">
        <v-text-field
          v-for="(param, i) in searchParams"
          flat
          solo-inverted
          v-bind:key="i"
          prepend-icon="search"
          @keyup.enter="loadData()"
          @blur="loadData()"
          :label="param['label']"
          v-model="filterParams[param['name']]"
          :append-icon="param['name'] ? 'clear' : ''"
          :append-icon-cb="clear"
          ></v-text-field>
      </div>
      <v-divider></v-divider>
      <div class="table__overflow">
        <table class="datatable table mo-table">
          <thead>
          <tr>
            <th class="td-checkbox" v-if="showCheckboxAll">
              <v-checkbox
                primary
                hide-details
                @change="toggleAll()"
                v-model="checkboxAll.selected"
                :indeterminate="checkboxAll.indeterminate"
              ></v-checkbox>
            </th>
            <!--<th class="td-checkbox">No</th>-->
            <th class="column sortable"
                v-bind:class="{'text-xs-left': column.type !== 'checkbox'}"
                v-for="column in columns">
              <!--<i aria-hidden="true" class="material-icons icon">arrow_upward</i>-->
              {{column.label}}
            </th>
            <th class="column-action">操作</th>
          </tr>
          <tr class="datatable__progress">
            <th colspan="100%" class="column"></th>
          </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in datasource"
                v-if="datasource && datasource.length > 0"
                >
              <td v-if="showCheckboxAll">
                <v-checkbox
                  primary
                  hide-details
                  v-model="item.checked"
                  @change="unCheckedAll()"
                ></v-checkbox>
              </td>
              <!--<td class="td-checkbox text-xs-center">{{pagingInfo.startRow + index}}</td>-->
              <td v-bind:class="{'text-xs-left': col.type !== 'checkbox'}"
                  v-for="col in columns">
                <div v-if="!col.type || col.type === 'text'">
                  <a v-if="col.link" @click="view(item)">{{item[col.name]}}</a>
                  <span v-if="!col.link">{{item[col.name]}}</span>
                </div>
                <div v-if="col.type === 'checkbox'" class="checkbox">
                  <v-checkbox
                    disabled
                    v-model="item[col.name]"
                  ></v-checkbox>
                </div>
                <div v-if="col.type === 'avatar' && item[col.name] != null">
                  <mo-avatar size="26px" :avatarModel="item[col.name]" :avatarLabel="item[col.name][col.itemText]"></mo-avatar>
                  <!--<v-avatar size="26px">-->
                    <!--<img :src="item[col.name]['avatarUrl']" :alt="item[col.name][col.itemText]">-->
                  <!--</v-avatar>-->
                  <!--<span style="padding-left: 10px">{{item[col.name][col.itemText]}}</span>-->
                </div>
              </td>
              <td class="column-action">
                <v-menu bottom left>
                  <v-btn icon flat slot="activator">
                    <v-icon small>more_vert</v-icon>
                  </v-btn>
                  <v-list>
                    <v-list-tile @click="btn.click(item)"
                                 v-for="(btn, index) in tableButtons"
                                 v-bind:key="index">
                      <v-list-tile-title style="font-size: 12px">{{btn.label}}</v-list-tile-title>
                    </v-list-tile>
                  </v-list>
                </v-menu>
                <!--<v-speed-dial-->
                  <!--v-model="item['actionFab']"-->
                  <!--direction="bottom"-->
                <!--&gt;-->
                  <!--<v-btn-->
                    <!--slot="activator"-->
                    <!--v-model="item['actionFab']"-->
                    <!--color="blue darken-2"-->
                    <!--small-->
                    <!--dark-->
                    <!--fab-->
                    <!--flat-->
                  <!--&gt;-->
                    <!--<v-icon>more_vert</v-icon>-->
                    <!--<v-icon>close</v-icon>-->
                  <!--</v-btn>-->
                  <!--<v-tooltip left v-for="(btn, index) in tableButtons" v-bind:key="index">-->
                    <!--<v-btn-->
                      <!--slot="activator"-->
                      <!--fab-->
                      <!--dark-->
                      <!--small-->
                      <!--color="green"-->
                      <!--@click="btn.click(item)"-->
                    <!--&gt;-->
                      <!--<v-icon>{{btn.icon}}</v-icon>-->
                    <!--</v-btn>-->
                    <!--<span>{{btn.label}}</span>-->
                  <!--</v-tooltip>-->
                <!--</v-speed-dial>-->
              </td>
            </tr>
            <tr>
              <td colspan="100%" class="text-xs-center" v-if="!buttons.search.loading && datasource && datasource.length === 0">
                <mo-empty-state></mo-empty-state>
              </td>
              <td colspan="100%" class="text-xs-center" v-if="buttons.search.loading">
                <div class="mo-table-loading">
                  <v-progress-circular indeterminate color="primary"></v-progress-circular>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <table class="datatable table mo-table">
        <tfoot>
        <tr>
          <td class="td-checkbox" v-if="showCheckboxAll">
            <v-checkbox
              primary
              hide-details
              @change="toggleAll()"
              v-model="checkboxAll.selected"
              :indeterminate="checkboxAll.indeterminate"
            ></v-checkbox>
          </td>
          <td class="column-action">
            <v-menu bottom offset-y>
              <v-btn v-if="showAttachButton" icon flat slot="activator">
                <v-icon small>fa-download</v-icon>
              </v-btn>
              <v-list>
                <v-list-tile @click="downloadTemplate()">
                  <v-list-tile-title>Excel 模板</v-list-tile-title>
                </v-list-tile>
                <v-divider></v-divider>
                <v-list-tile class="list__tile--link">
                  <v-list-tile-title>
                    <uploader :options="uploadOption">
                      <uploader-label>Excel 导入</uploader-label>
                    </uploader>
                  </v-list-tile-title>
                </v-list-tile>
                <v-list-tile @click="exportFile()">
                  <v-list-tile-title>Excel 导出</v-list-tile-title>
                </v-list-tile>
              </v-list>
            </v-menu>
          </td>
          <td>
            <div class="datatable__actions">
              <div class="datatable__actions__select">每页:
                <v-select
                  class="input-group--hide-details "
                  v-bind:items="pagingSizeOption"
                  v-model="maxSize"
                  single-line
                  bottom
                ></v-select>
              </div>
              <div class="datatable__actions__pagination">
                From {{pagingInfo.startRow}} to {{pagingInfo.endRow}}
              </div>
              <button type="button" class="btn btn--flat btn--icon"
                      v-bind:class="{'btn--disabled': !pagingInfo.hasPreviousPage}"
                      @click="skipPage(pagingInfo.prePage)">
                <div class="btn__content"><i aria-hidden="true" class="material-icons icon">chevron_left</i></div>
              </button>
              <button type="button" class="btn btn--flat btn--icon"
                      v-for="num in pagingInfo.navigatepageNums"
                      v-bind:class="{'btn--disabled': num == pagingInfo.pageNum}"
                      @click="skipPage(num)">
                <div class="btn__content">{{num}}</div>
              </button>
              <button type="button" class="btn btn--flat btn--icon"
                      v-bind:class="{'btn--disabled': !pagingInfo.hasNextPage}"
                      @click="skipPage(pagingInfo.nextPage)">
                <div class="btn__content">
                  <i aria-hidden="true" aria-disabled="true" class="material-icons icon">chevron_right</i>
                </div>
              </button>
            </div>
          </td>
        </tr>
        </tfoot>
      </table>
    </div>
  </div>
</template>

<script>
  import _ from 'underscore'
  import MoEmptyState from './EmptyState'
  import buttons from './mixins/buttons'
  import pagination from './mixins/pagination'
  import checkbox from './mixins/checkbox'

  let vm
  export default {
    mixins: [
      buttons,
      pagination,
      checkbox
    ],
    components: {
      MoEmptyState
    },
    name: 'mo-table',
    created () {
      vm = this
      if (!!this.pagingSizeOption && this.pagingSizeOption.length > 0) {
        vm.pagingInfo.pageSize = this.pagingSizeOption[0]
      }
      vm.loadData()
    },
    props: {
      url: {
        require: true
      },
      searchParams: {
        require: false,
        default () {
          return {}
        },
        validator (param) {
          return true
        }
      },
      title: {
        require: false,
        default: ''
      },
      columns: {
        require: true,
        type: Array,
        validator (items) {
          for (let i = 0; i < items.length; i++) {
            if (!_.property('label')(items[i])) {
              return false
            }
            if (!_.property('name')(items[i])) {
              return false
            }
          }
          return true
        }
      },
      formColumns: {
        require: false,
        type: Array
      },
      events: {
        type: Array,
        require: false
      }
    },
    data () {
      return {
        datasource: [],
        buttons: {
          search: {
            loading: false
          }
        },
        filterParams: {},
        showSearch: true
      }
    },
    watch: {
      filterParams: function (data) {
        if (data.keyword) {
          vm.showSearch = true
        } else {
          vm.showSearch = false
        }
        vm.loadData()
      }
    },
    methods: {
      clear () {
        vm.filterParams.keyword = null;
        vm.loadData()
      },
      view (data) {
        vm.$emit('view', data)
      },
      add () {
        vm.$emit('view', {})
      },
      removeById (id) {
        vm.$confirm('是否删除数据？').then((data) => {
          let ids = [id]
          if (ids.length === 0) {
            return
          }
          vm.axios.delete(vm.url, {
            data: {
              ids: ids
            },
            disableGetBracketNotation: false
          }).then(() => {
            vm.selected = []
            vm.$alert('删除成功')
            vm.loadData()
          })
        })
      },
      remove () {
        vm.$confirm('是否删除所选数据？').then((data) => {
          let ids = []
          for (let i = 0; i < vm.selected.length; i++) {
            ids.push(vm.selected[i].id)
          }
          if (ids.length === 0) {
            return
          }
          vm.axios.delete(vm.url, {
            data: {
              ids: ids
            },
            disableGetBracketNotation: false
          }).then(() => {
            vm.selected = []
            vm.$alert('删除成功')
            vm.loadData()
          })
        })
      },
      loadData () {
        if (vm.filterParams.keyword) {
          vm.showSearch = true
        } else {
          vm.showSearch = false
        }
        // clear checkbox state
        vm.clearCheckState()
        let params = {
          pageSize : vm.pagingInfo.pageSize,
          pageNum : vm.pagingInfo.pageNum
        }
        params = _.extend(params, vm.filterParams)
        vm.datasource = []
        vm.axios.get(vm.url, {
          params: params
        }).then((response) => {
          // 还是由于 JavaScript 的限制，Vue 不能检测对象属性的添加或删除，所以必须使用 vm.$set()或者Vue.set()
//          vm.$set(vm.tableOptions, 'datasource', response.data.data)
//          vm.$set(vm.tableOptions, 'pagingInfo', response.data.pageInfo)
          vm.datasource = response.data.data
          vm.pagingInfo = response.data.pageInfo
        })
      }
    }
  }
</script>

<style>
  .mo-table-loading {
    top: 50%;
    left: 50%;
    height: 200px;
    font-size: 16px;
    text-align: center;
  }
  .mo-table-loading .progress-circular {
    margin-top: 75px;
  }
  .td-checkbox {
    width: 48px;
  }
  table.mo-table {
    min-height: 532px;
  }
  table.mo-table thead th:first-child,
  table.mo-table tbody td:first-child,
  table.mo-table tbody td.td-checkbox,
  table.mo-table thead th.td-checkbox,
  table.mo-table tfoot th.td-checkbox,
  table.mo-table tfoot td.td-checkbox{
    /*padding: 0 10px;*/
    width: 32px;
  }
  table.mo-table tbody td {
    white-space: nowrap;
  }
  table.mo-table td.column-action {
    width: 50px;
  }
  table.mo-table tfoot td.column-action {
    width: 50px;
    padding: 0 0 !important;
  }
</style>
