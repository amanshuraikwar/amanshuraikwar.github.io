package io.github.amanshuraikwar.portfolio

import io.github.amanshuraikwar.portfolio.markdown.MdNode

class MdDataStore {

	fun getData(): List<MdNode> {
		return listOf(
            MdNode.H1(
                text = "A two line blog title"
            ),
            MdNode.Date(
                text = "DECEMBER 7, 2021"
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.Img(
                label = "This is an Image",
                url = "",
            ),
            MdNode.Spacer,
            MdNode.H3(
                text = "This is a second level heading or a section-heading"
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.P(
                text = "Youâ€™re an iOS developer. Maybe youâ€™ve been doing it for a while, or maybe youâ€™re new to the scene. Maybe you prefer to think of yourself as an â€œiOS engineer,â€ because it sounds better and no one seems to agree on whether thereâ€™s a difference. Whatever the situation: youâ€™re feeling restless. Youâ€™ve worked on interesting projects before â€” successful ones, even â€” and you recognize that getting paid to do what you do is in many ways a privilege. Nonetheless, you canâ€™t shake the feeling that none of the work youâ€™ve done is truly yours. Much of your time has been spent helping others achieve their goals â€” and thatâ€™s okay! â€” but you increasingly feel like youâ€™ve got some of your own goals to chase too."
            ),
            MdNode.Spacer,
            MdNode.Spacer,
            MdNode.Btn(
                text = "[Get it on Play Store",
                url = "",
            ),
		)
	}
}
