# Readme - Bowling technical test submission by James Bowkett#

## Notes on unpacking ##

 * I used Git for source control, and all .git meta data should be present,
 this should also work once the tarball is unpacked.
 * I used Maven 3 to prepare the builds, see the pom.xml for details.
 * The released jar is in target/bowling-1.0.jar.
 * To run the scoring system, type : `java -jar bowling-1.0.jar`
 (from the target directory)

## Notes on Implementation ##

 * This code was implemented using Java 7, running under intelli-j (so as this
 tarball is unpacked, the project should be ready to go).
 * I used cucumber-JVM for BDD. See `src/test/features/scoring.feature`
 * I used Junit for TDD
 * I used mockito for mocking in Junit
 * My main principles for coding were based on Uncle Bob's "Clean Code" hence,
 the numerous private helper methods and sparse commenting.  To that end, I am
 very pleased with how GameState.getNextTurn() and the methods in the scorer are
 implemented, because their difficulty is their fiddly edge cases around
 previous or next scores.

## What's wrong with this implementation ##

 * Some of the abstractions started to leak towards the end, such as the
 notion of a strike or a spare.
 * The cucumber coverage could be more far-reaching.
 * The team scoring is done right at the end of the game, when in fact it
 should have been kept track of during the game.
 * A Frame abstraction was probably needed.