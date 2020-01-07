<template>
  <v-app>
  <v-app-bar
      app
      color="cyan darken-3"
      dark
    >
        <span class="display-1 font-italic"> COSMETIC SHOP </span>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-content class="teal lighten-3">
      <v-col></v-col>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
  
  }),
};
</script>
