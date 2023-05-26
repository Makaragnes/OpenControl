package main

import (
	"fmt"
	"log"
	"net/http"
	"encoding/json"
)

type Article struct{
	Title string `json:"Title"`
	Desc string `json:"Desc"`
	Content string `json:"Content"`	
}

type Articles []Article

func AllArticles(w http.ResponseWriter, r *http.Request) {
	articles := Articles {
		Article {Title:"Test title", Desc:"Test description", Content:"Hellow"},
		Article {Title:"Test title", Desc:"Test description", Content:"Hellow"},
	}
	json.NewEncoder(w).Encode(articles)
}


// this is a comment

func main() {
	handleRequest()
}

func homePage(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "HomeEndpointHint")
}

func handleRequest() {
	http.HandleFunc("/", homePage)
	http.HandleFunc("/articles", AllArticles)
	log.Fatal(http.ListenAndServe(":8081", nil))
}