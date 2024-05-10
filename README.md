# rick for simple Github api data flow
1. I created two data models that one is for getting the list of users
   and the other one for getting information of a specific user, and referring
   to the kotlin file "UsersListModel" please.
2. There are two urls are used to get data.(See the file "GithubApiService")
   https://api.github.com/users?since=x&per_page=xx (to get list of users' data)
   https://api.github.com/users/{login} (to get a user's data, i.e. login=login_username)
3. All http requests(networking) are performed through Retrofit.(See the file AppModule)
4. In the directory "ui", data change are observed.
5. "viewModel" files get data with data flow: "Github API -> Model(repository) -> viewModel -> View
6. As the above Item 5, View(activities) would obtain data and refresh View therefore.
7. Note please that the database storing (Room) and Pagination are not implemented for simplicity.
8. According the specification, site_admin(Badge) should be displayed but I can get (not all) only
   Boolean value false if I haven't misunderstood the meaning of site administration.

