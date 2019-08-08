workflow "Build" {
  resolves = ["mvn"]
  on = "push"
}

workflow "PR Build" {
  resolves = ["mvn"]
  on = "pull_request"
}

action "mvn" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  args = "clean test package"
}
