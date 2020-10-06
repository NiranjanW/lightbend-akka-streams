resolvers in ThisBuild += "lightbend-commercial-mvn" at
  "https://repo.lightbend.com/pass/WB2nP6Zg4E54EkkRJ2nYAInONyi6ZvCKmWKHAo-6Kc9o-KGs/commercial-releases"
resolvers in ThisBuild += Resolver.url("lightbend-commercial-ivy",
  url("https://repo.lightbend.com/pass/WB2nP6Zg4E54EkkRJ2nYAInONyi6ZvCKmWKHAo-6Kc9o-KGs/commercial-releases"))(Resolver.ivyStylePatterns)