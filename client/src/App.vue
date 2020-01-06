<template>
  <v-app>
  <v-app-bar
      app
      color="cyan darken-3"
      dark
    >
        <span class="display-1 font-italic"> ADD EMPLOYEE </span>
      <v-spacer></v-spacer>
      <v-btn
      dark
      color = "red darken-1"
      >
        <span class="mr-2">log out</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>
    <v-content class="teal lighten-3">
      <v-col></v-col>
      <Employee/>
    </v-content>
 
  </v-app>
</template>

<script>
import Employee from './components/Employee';

export default {
  name: 'App',

  components: {
    Employee,
  },

  data: () => ({
    //
  }),
};
</script>
