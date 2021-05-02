<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by title"
          v-model="title"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
            @click="searchTitle"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Contents List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(content, index) in contents"
          :key="index"
          @click="setActiveContents(content, index)">
          {{ content.title }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllContents">
        Remove All
      </button>
    </div>
    <div class="col-md-6">
      <div v-if="currentContents">
        <h4>Contents</h4>
        <div>
          <label><strong>Title:</strong></label> {{ currentContents.title }}
        </div>
        <div>
          <label><strong>Description:</strong></label> {{ currentContents.contents }}
        </div>
        <!-- <div>
          <label><strong>Status:</strong></label> {{ currentContents.published ? "Published" : "Pending" }}
        </div> -->

        <a class="badge badge-warning"
          :href="'/contents/' + currentContents.id"
        >
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Contents...</p>
      </div>
    </div>
  </div>
</template>

<script>
import ContentsDataService from "../services/ContentsDataService";

export default {
  name: "contents-list",
  data() {
    return {
      contents: [],
      currentContents: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveContents() {
      ContentsDataService.getAll()
        .then(response => {
          this.contents = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    refreshList() {
      this.retrieveContents();
      this.currentContents = null;
      this.currentIndex = -1;
    },

    setActiveContents(content, index) {
      this.currentContents = content;
      this.currentIndex = index;
    },

    removeAllContents() {
      ContentsDataService.deleteAll()
        .then(response => {
          console.log(response.data);
          this.refreshList();
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    searchTitle() {
      ContentsDataService.findByTitle(this.title)
        .then(response => {
          this.contents = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveContents();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>