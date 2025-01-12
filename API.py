import json

from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from sqlalchemy.exc import SQLAlchemyError

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://postgres:bazepodataka@localhost:5432/serieATeams'
db = SQLAlchemy(app)
app.config['SQLALCHEMY_ECHO'] = True

class Team(db.Model):
    __tablename__ = 'teams'
    teamid = db.Column(db.Integer, primary_key=True)
    team = db.Column(db.String(100), nullable=False)
    goals = db.Column(db.Integer, nullable=False)
    shotspg = db.Column(db.Float, nullable=False)
    yellow_cards = db.Column(db.Integer, nullable=False)
    red_cards = db.Column(db.Integer, nullable=False)
    avgpossessionperc = db.Column(db.Float, nullable=False)
    avgpassaccperc = db.Column(db.Float, nullable=False)
    aerialswon = db.Column(db.Float, nullable=False)
    rating = db.Column(db.Float, nullable=False)

    def as_dict(self):
        return {c.name: getattr(self, c.name) for c in self.__table__.columns}

class Player(db.Model):
    __tablename__ = 'players'
    playerid = db.Column(db.Integer, primary_key=True)
    playerfirstname = db.Column(db.String(100), nullable=False)
    playerlastname = db.Column(db.String(100), nullable=False)
    position = db.Column(db.String(100), nullable=False)
    teamid = db.Column(db.Integer, db.ForeignKey('teams.teamid'), nullable=False)

    def as_dict(self):
        return {c.name: getattr(self, c.name) for c in self.__table__.columns}

with app.app_context():
    db.create_all()


def response_wrapper(data, context,message="Success", status=200):
    return jsonify({
        "status": status,
        "@context": context,
        "message": message,
        "data": data
    }), status

@app.errorhandler(404)
def not_found_error(error):
    return response_wrapper(None, "The requested URL was not found on the server. If you entered the URL manually please check your spelling and try again.", 404)

@app.route('/teams', methods=['GET'])
def get_teams():
    try:
        teams = Team.query.all()
        return response_wrapper([team.as_dict() for team in teams],context={
            "@vocab": "http://schema.org/",
            "team": "name",
            "teamid": "identifier"
        })
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "","Database error", 500)

@app.route('/teams/name/<string:team>', methods=['GET'])
def get_team_by_name(team):
    try:
        team = Team.query.filter_by(team=team).first_or_404()
        return response_wrapper(team.as_dict(),context={
            "@vocab": "http://schema.org/",
            "team": "name",
            "teamid": "identifier"
        })
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "","Database error", 500)

@app.route('/teams/<int:teamid>/players', methods=['GET'])
def get_players_from_team(teamid):
    try:
        players = Player.query.filter_by(teamid=teamid).all()
        return response_wrapper([player.as_dict() for player in players],context={
            "@vocab": "http://schema.org/",
            "playerfirstname": "givenName",
            "playerlastname": "familyName",
            "teamid": "identifier"
        })
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "", "Database error", 500)

@app.route('/teams/<int:teamid>', methods=['GET'])
def get_team(teamid):
    try:
        team = Team.query.get_or_404(teamid)
        return response_wrapper(team.as_dict(),context={
            "@vocab": "http://schema.org/",
            "team": "name",
            "teamid": "identifier"
        })
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "","Database error", 500)

@app.route('/teams/name/<string:team>/players', methods=['GET'])
def get_players_by_team_name(team):
    try:
        players = db.session.query(Player).join(Team).filter(Team.team == team).all()
        return response_wrapper([player.as_dict() for player in players], context={
        "@vocab": "http://schema.org/",
        "playerfirstname": "givenName",
        "playerlastname": "familyName",
        "teamid": "identifier"})
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "","Database error", 500)

@app.route('/teams/<string:teamid>/players/position/<string:position>', methods=['GET'])
def get_player(teamid, position):
    try:
        player = Player.query.filter_by(teamid=teamid, position=position).first_or_404()
        return response_wrapper(player.as_dict(), context={
            "@vocab": "http://schema.org/",
            "playerfirstname": "givenName",
            "playerlastname": "familyName",
            "teamid": "identifier"
        })
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "", "Database error", 500)



@app.route('/teams', methods=['POST'])
def add_team():
    try:
        data = request.get_json()
        new_team = Team(**data)
        print(new_team.teamid)
        db.session.add(new_team)
        db.session.commit()
        return response_wrapper(new_team.as_dict(), context={
            "@vocab": "http://schema.org/",
            "team": "name",
            "teamid": "identifier"
        }, message= "Team added", status=201)
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "","Database error", 500)

@app.route('/teams/<int:teamid>', methods=['PUT'])
def update_team(teamid):
    try:
        data = request.get_json()
        team = Team.query.get_or_404(teamid)
        for key, value in data.items():
            setattr(team, key, value)
        db.session.commit()
        return response_wrapper(team.as_dict(), context={
            "@vocab": "http://schema.org/",
            "team": "name",
            "teamid": "identifier"
        },message="Team updated")
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "", "Database error", 500)

@app.route('/teams/<int:teamid>', methods=['DELETE'])
def delete_team(teamid):
    try:
        team = Team.query.get_or_404(teamid)
        db.session.delete(team)
        db.session.commit()
        return response_wrapper(None,
        context={
            "@vocab": "http://schema.org/",
            "team": "name",
            "teamid": "identifier"
        }
        , message="Team deleted")
    except SQLAlchemyError as e:
        return response_wrapper(str(e), "", "Database error", 500)

@app.route('/openapi', methods=['GET'])
def get_openapi_spec():
    try:
        with open('openapi.json', 'r') as file:
            spec = file.read()
        return jsonify(json.loads(spec))
    except Exception as e:
        return response_wrapper(str(e), "", message="Error reading OpenAPI spec", status=500)

if __name__ == '__main__':
    app.run(debug=True)