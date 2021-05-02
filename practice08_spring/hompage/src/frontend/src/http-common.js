/*
 * BeKoder블로그 참고부분:
 * use axios to fetch data
 */

import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8800/api",
  headers: {
    "Content-type": "application/json"
  }
});