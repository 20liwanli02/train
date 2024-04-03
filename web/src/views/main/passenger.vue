<template>
  <p>
  <a-button type="primary" @click="showModal">新增</a-button>
  </p>
  <a-table :dataSource="dataSource" :columns="columns" />
  <a-modal v-model:visible="visible" title="乘车人" @ok="handleOk" ok-text="确认" cancel-text="取消">
    <a-form :model="passenger" :label-col="{span: 4}" :wrapper-col="{ span: 20 }">
      <a-form-item label="姓名">
        <a-input v-model:value="passenger.name" />
      </a-form-item>
      <a-form-item label="身份证">
        <a-input v-model:value="passenger.idCard" />
      </a-form-item>
      <a-form-item label="旅客类型">
        <a-select v-model:value="passenger.type">
          <a-select-option value="1">成人</a-select-option>
          <a-select-option value="2">儿童</a-select-option>
          <a-select-option value="3">学生</a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>


<script>
import {defineComponent,ref,reactive} from "vue";
import {notification} from "ant-design-vue";
import axios from "axios";


export default defineComponent({
  setup() {
    const visible = ref(false);
    const passenger = reactive({
      id: undefined,
      memberId: undefined,
      name: undefined,
      idCard: undefined,
      type: undefined,
      createTime: undefined,
      updateTime: undefined,
    });

    const dataSource = [
          {
            key: '1',
            name: '胡彦斌',
            age: 32,
            address: '西湖区湖底公园1号',
          },
          {
            key: '2',
            name: '胡彦祖',
            age: 42,
            address: '西湖区湖底公园1号',}];

        const columns = [
          {
            title: '姓名',
            dataIndex: 'name',
            key: 'name',
          },
          {
            title: '年龄',
            dataIndex: 'age',
            key: 'age',
          },
          {
            title: '住址',
            dataIndex: 'address',
            key: 'address',
          },];
    // const passengers = ref([]);
    // // 分页的三个属性名是固定的
    // const pagination = ref({
    //   total: 0,
    //   current: 1,
    //   pageSize: 10,
    // });
    // let loading = ref(false);
    // const columns = [
    //   {
    //     title: '姓名',
    //     dataIndex: 'name',
    //     key: 'name',
    //   },
    //   {
    //     title: '身份证',
    //     dataIndex: 'idCard',
    //     key: 'idCard',
    //   },
    //   {
    //     title: '旅客类型',
    //     dataIndex: 'type',
    //     key: 'type',
    //   },
    //   {
    //     title: '操作',
    //     dataIndex: 'operation'
    //   }
    // ];

    const showModal = () => {
      visible.value = true;
    };

    const handleOk = () => {
      axios.post("/member/passenger/save", passenger).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({description: "保存成功！"});
          visible.value = false;
          // handleQuery({
          //   page: pagination.value.current,
          //   size: pagination.value.pageSize
          // });
        } else {
          notification.error({description: data.message});
        }
      });
    };


    return {
      visible,
      showModal,
      handleOk,
      passenger,
      dataSource,
      columns,
    };
  },
})
</script>


<style>

</style>

