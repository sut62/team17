
<template>
  <v-container>
      <v-card color="teal lighten-5">
          <v-data-table
            :headers="headers"
            :items="Show_PreOrder"
            hide-default-header
            hide-default-footer
            class="elevation-1"
  ></v-data-table>
      </v-card>
  </v-container>
</template>

<script>
export default {
  name: 'Show_PreOrder',
  data () {
      return {
        headers: [
          {
            text: 'AAAA',
            align: 'left',
            value: 'pre_order',
          },
          { text: 'Date', value: 'date' },
          { text: 'Name', value: 'cus_name' },
          { text: 'Tel', value: 'tel' },
          { text: 'Brand', value: 'brand' },
          { text: 'Type', value: 'type_Product' },
          { text: 'Quantity', value: 'quanrity' },
        ]
      }
  }
}
</script>


