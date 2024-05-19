function wildWestAdventure(input) {
  const MAX_BULLETS = 6;
  const MAX_HP = 100;
  const posse = {};
  const actions = {
    FireShot: (name, target) => {
      if (posse[name].bullets > 0) {
        posse[name].bullets -= 1;
        console.log(
          `${name} has successfully hit ${target} and now has ${posse[name].bullets} bullets!`
        );
      } else {
        console.log(
          `${name} doesn't have enough bullets to shoot at ${target}!`
        );
      }
    },
    TakeHit: (name, damage, attacker) => {
      posse[name].HP -= damage;
      if (posse[name].HP > 0) {
        console.log(
          `${name} took a hit for ${damage} HP from ${attacker} and now has ${posse[name].HP} HP!`
        );
      } else {
        console.log(`${name} was gunned down by ${attacker}!`);
        delete posse[name];
      }
    },
    Reload: (name) => {
      const bulletsToReload = MAX_BULLETS - posse[name].bullets;
      if (bulletsToReload > 0) {
        posse[name].bullets = MAX_BULLETS;
        console.log(`${name} reloaded ${bulletsToReload} bullets!`);
      } else {
        console.log(`${name}'s pistol is fully loaded!`);
      }
    },
    PatchUp: (name, amount) => {
      if (posse[name].HP < MAX_HP) {
        const healedHP = Math.min(amount, MAX_HP - posse[name].HP);
        posse[name].HP += healedHP;
        console.log(`${name} patched up and recovered ${healedHP} HP!`);
      } else {
        console.log(`${name} is in full health!`);
      }
    },
  };

  const n = parseInt(input.shift(), 10);
  for (let i = 0; i < n; i++) {
    const [name, HP, bullets] = input.shift().split(" ");
    posse[name] = {
      HP: parseInt(HP, 10),
      bullets: parseInt(bullets, 10),
    };
  }

  let command = input.shift();
  while (command !== "Ride Off Into Sunset") {
    const [action, ...params] = command.split(" - ");
    if (actions[action]) {
      actions[action](...params);
    }
    command = input.shift();
  }

  Object.keys(posse).forEach((name) => {
    console.log(
      `${name}\n HP: ${posse[name].HP}\n Bullets: ${posse[name].bullets}`
    );
  });
}
