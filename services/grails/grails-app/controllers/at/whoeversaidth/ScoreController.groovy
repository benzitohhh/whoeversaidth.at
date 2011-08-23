package at.whoeversaidth

import grails.converters.JSON

class ScoreController {
	static String QUERY = "FROM Game as g WHERE g.fbid in (:fbids) ORDER BY g.score DESC"
	static String QUERY_ALL = "FROM Game as g ORDER BY g.score DESC"
	static int MAX = 5
	
    /*
     * returns list of scores
     * GET /score/$fbid?friends=$fbid1,$fbid2,...,$fbidN
     * http://localhost:8080/whoeversaidthat_services/score/123?friends=456,321
     * 
     * also /score returns list of all scores
     * takes optional "max" param (to specify max number of results)
     */
	def showScores = {
		String fbid = params.fbid
		List fbids = [fbid];
		if (params["friends"]) {
			fbids.addAll(params.friends.split(","))
		}
		int max = MAX
		if (params["max"]) {
			max = new Integer(params["max"])
		}
		// TODO: validation
		
		List games
		if (params["fbid"]) {
			games = Game.findAll(QUERY, [fbids:fbids, max:max])
		} else {
			games = Game.findAll(QUERY_ALL, [max:max])
		}
		render games as JSON
	}
	
	/*
	* POST /score/$fbid [name=xxx,score=yyy]
	* curl -d "name=Ben%20Immanuel&score=1" http://localhost:8080/whoeversaidthat_services/score/123
	*/
	def saveScores = {
		String fbid = params.fbid
		String name = params.name
		String score = params.score
		// TODO: validation
		new Game(fbid: fbid, name: name, score: score).save()
		render "saved ok\n"
	}
}
